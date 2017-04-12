class Hotel < ApplicationRecord
    has_one :manager
    has_many :rooms
    has_many :trips, through: :hotel_bookings

    # validates :manager, :presence => true
end
