class AdminController < ApplicationController
  before_action :check_admin

  def add_hotel
      hotel_name = params["hotel_name"]
      state = params["state"]
      numrooms = params["number_of_rooms"]
      manager_email = params["manager_email"]
      r = @current_user.add_hotel(hotel_name,state,numrooms,manager_email)
      if r[0]
          render json: {errors: "",
                        status: STATUS_CREATED,
                        hotel: r[1].as_json(:include => [:rooms,:manager =>{ include: :user }])}
      else
          render json: {errors: r[1],status: STATUS_BAD_REQUEST}
      end
  end


  private
  def check_admin
      if @current_user.role == ROLE_ADMIN
          true
      else
          render json: {errors: "Not Admin", status: STATUS_BAD_REQUEST}
          false
      end
  end
end
