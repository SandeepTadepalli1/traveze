class HotelBooking < ApplicationRecord
    belongs_to :hotel
    belongs_to :trip
end
