class ManagerController < ApplicationController
    before_action :check_manager
    before_action :get_info

    def edit_hotel
        if @hotel.update(hotel_params)
            render json: {status: STATUS_OK, hotel:@hotel.as_json(:include => [:rooms,:manager =>{ include: :user }]) }
        else
            render json: {status: STATUS_BAD_REQUEST, errors: @hotel.errors}
        end
    end

    def update_room_cost
        if @manager.update_room_cost(params["room_id"],params["new_cost"])
            render json: {status: STATUS_OK}
        else
            render json: {status: STATUS_BAD_REQUEST}
        end
    end

    def num_rooms_booked_given_date
        num_rooms_booked = @manager.number_of_rooms_booked(Date.parse(params["date"]))
        render json: {status: STATUS_OK, num_rooms_booked: num_rooms_booked}
    end


    private
    def get_info
        @manager = Manager.find_by_user_id(@current_user.id)
        @hotel = @manager.hotel
    end
    def check_manager
        if @current_user.role == ROLE_HOTEL_MANAGER
            true
        else
            render json: {errors: "Not Mannager", status: STATUS_BAD_REQUEST}
            false
        end
    end
    def hotel_params
        params.require(:hotel).permit(:name,:state,:numberofrooms)
    end
end
