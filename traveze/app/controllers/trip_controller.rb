class TripController < ApplicationController
    def get_hotel_location
        p  = Place.find(params[:id])
        render json: {hotel: p.hotels.as_json(include: :rooms)}
    end
end
