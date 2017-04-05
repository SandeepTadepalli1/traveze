class AuthenticationController < ApplicationController
 skip_before_action :authenticate_request

 # @post
 def authenticate
   command = AuthenticateUser.call(params[:email], params[:password])

   if command.success?
     render json: { auth_token: command.result,status: STATUS_OK }
   else
     render json: { error: command.errors,status: STATUS_UNAUTHORIZED}
   end
 end

 # @Post
 def register
   @user = User.new(user_params)
   if @user.save
     render json: {errors:"",status: :created}
   else
     render json: {errors: @user.errors, status: :bad_request}
   end
 end

 private

 def user_params
   params.require(:user).permit(:name, :email, :password, :password_confirmation)
 end
end