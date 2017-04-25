class HotelController < ApplicationController
    def rooms
        @hotel = Hotel.find(params['hotel_id'])
        render json:{rooms: @hotel.rooms.as_json,status: STATUS_OK}
    end
end
