class Trip < ApplicationRecord
    belongs_to :user
    has_one :hotel_booking
    has_one :hotel, through: :hotel_booking
end
