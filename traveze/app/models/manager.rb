class Manager < ApplicationRecord
    belongs_to :user
    belongs_to :hotel

    def change_num_of_rooms(newrooms)
        h = self.hotel
        h.numberofrooms = newrooms
        if h.save
            true
        else
            h.errors
        end
    end

    def number_of_rooms_booked(date)
        h = self.hotel
        count = 0
        h.hotel_bookings.each do |hb|
            if hb.start_date <= date && date  <= hb.end_date
                count += 1
            end
        end
        count
    end

    def update_room_cost(room_id,new_cost)
        begin
            @room = self.hotel.rooms.find(room_id)
            if @room
                @room.cost = new_cost
                @room.save
                true
            else
                false
            end
        rescue ActiveRecord::RecordNotFound => e
            false
        end

    end

  end