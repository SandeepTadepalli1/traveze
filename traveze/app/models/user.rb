class User < ApplicationRecord
    has_secure_password
    before_save :downcase_email

    validates_presence_of :email,:name,:mobilenumber
    validates_uniqueness_of :email
    has_many :trips


    def is_manager
        if self.role != ROLE_HOTEL_MANAGER
            false
        else
           true
        end
    end

    def add_hotel(hotel_name,state,numberofrooms,manager_email,place)
        if check_admin?
            @place = Place.find_by_name(place)
            @hotel = Hotel.new(name:hotel_name,
                               state: state,
                               numberofrooms: numberofrooms,
                               place_id: @place.id)
            @manager_user = User.find_by_email(manager_email)
            if @manager_user
                @manager = @hotel.build_manager(user_id: @manager_user.id)
                unless @manager.valid?
                    @hotel.errors[:base] << "Not a valid email"
                    return false,@hotel.errors
                end
                @manager_user.role = ROLE_HOTEL_MANAGER
                @manager_user.save
                @hotel.save
                @hotel.add_dummy_rooms
                return true,@hotel
            else
                @hotel.errors[:manager] = "Manager email required"
                return false,@hotel.errors
            end
        else
            error = "Not a admin"
            error.add(:base, error)
            return false,error
        end
    end



    # takes a trip object
    # trip object should be of the form
    # trip = {
    #   num_guests: "10",
    #   hotel: {
    #     id: "10",
    #     room_id: "34"
    #     start_date: "2017-04-20"
    #     end_date  : "2017-04-21"
    #   }
    #   bus: {
    #     bus_id: 10
    #     num   _seats; 25
    #   }
    # }
    def create_trip(trip)
        t = Trip.new(user_id: self.id)
        t.num_guests = trip["num_guests"]
        hotel = trip["hotel"]
        if hotel
            h = Hotel.find(hotel["id"])
            t.build_hotel_booking(hotel_id: h.id)
            t.hotel_booking.start_date = Date.parse(hotel["start_date"])
            t.hotel_booking.end_date = Date.parse(hotel["end_date"])
        end
        if t.save
            true
        else
            t.errors
        end
    end
    def check_admin?
        if self.role == ROLE_ADMIN
            true
        else
            false
        end
    end

    private
    def downcase_email
        if self.email
            self.email = self.email.delete(' ').downcase
        end
    end

end
