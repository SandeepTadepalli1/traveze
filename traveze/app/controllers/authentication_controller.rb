class AuthenticationController < ApplicationController
 skip_before_action :authenticate_request

 # @post
 def authenticate
   command = AuthenticateUser.call(params[:email], params[:password])

   if command.success?
     render json: { auth_token: command.result }
   else
     render json: { error: command.errors }, status: :unauthorized
   end
 end

 # @Post
 def register
   @user = User.new(user_params)
   if @user.save
     render json: @user, status: :created
   else
     render json: @user.errors, status: :unprocessable_entity
   end
 end

 private

 def user_params
   params.require(:user).permit(:name, :email, :password, :password_confirmation)
 end

end