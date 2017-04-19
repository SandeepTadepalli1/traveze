class AuthenticationController < ApplicationController
 skip_before_action :authenticate_request

 # @post
 def authenticate
   command = AuthenticateUser.call(params[:email], params[:password])

   if command.success?
       @current_user = User.find_by_email(params[:email])
       render json: { auth_token: command.result,status: STATUS_OK,user: @current_user }
   else
     render json: { error: command.errors,status: STATUS_UNAUTHORIZED }
   end
 end

 # @Post

end