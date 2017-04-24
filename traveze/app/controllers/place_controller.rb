class PlaceController < ApplicationController
    def hotels
        place = params["place"]
        @place = Place.find_by_name(place.downcase)
        render json: {hotels: @place.hotels.as_json,status:STATUS_OK}
    end
end
