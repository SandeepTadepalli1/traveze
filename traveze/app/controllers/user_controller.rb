class UserController < ApplicationController
  skip_before_action :authenticate_request, :only => [:register]

  def register
    @user = User.new(user_params)
    #
    if @user.save
      render json: { errors:"",status: STATUS_CREATED,user: @user, auth_token:AuthenticateUser.call(user_params["email"],user_params["password"]).result}
    else
      render json: { errors: @user.errors, status: STATUS_BAD_REQUEST }
    end
  end

  def update
    if @current_user.update(user_params)
      render json: {errors: "",status: STATUS_OK, user: @current_user}
    else
        render json: {errors: @current_user.errors, status: STATUS_BAD_REQUEST}
    end
  end

  def create_trip
    if @current_user.create_trip(params['trip'])
      render json: STATUS_OK
    else
      render json: STATUS_BAD_REQUEST
    end
  end

  private
  def user_params
    params.require(:user).permit(:name, :email, :password, :password_confirmation,:mobilenumber)
  end
end
