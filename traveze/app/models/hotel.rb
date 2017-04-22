class Hotel < ApplicationRecord
    belongs_to :place
    has_one :manager
    has_many :rooms, inverse_of: :hotel,:dependent => :delete_all
    has_many :hotel_bookings
    has_many :trips, through: :hotel_bookings


    # validates :manager, :presence => true
    def add_dummy_rooms
        room_normal = 1100
        room_delux = 1101
        room_three_beds = 1102
        room_four_beds =1103
        room_duplex= 1104
        l = [room_normal,room_delux,room_three_beds,room_four_beds,room_duplex]
        rand(2..5).times do
            @room = self.rooms.build
            @room.cost = Faker::Number.number(rand(3..4)).to_i
            @room.room_type = l.sample
            @room.save
        end
    end
end
