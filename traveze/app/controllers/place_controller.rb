class PlaceController < ApplicationController
    def get_hotels
        place = params["place"]
        @place = place.find_by_name(place)
        render json:{hotels: @place.hotels.as_json(include: :rooms),status:STATUS_OK}
    end
end
