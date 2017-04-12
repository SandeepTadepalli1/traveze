class Trip < ApplicationRecord
    belongs_to :user
    has_one :hotel, through: :hotel_bookings
end
