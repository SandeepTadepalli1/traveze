class ApplicationController < ActionController::API
  before_action :authenticate_request
  before_action :getuser

  attr_reader :current_user
  private

  def authenticate_request
      @current_user = AuthorizeApiRequest.call(request.headers).result
      render json: { error: 'Not Authorized',status: STATUS_UNAUTHORIZED }, status: :ok unless @current_user
  end

    def getuser
        @current_user = AuthorizeApiRequest.call(request.headers).result
    end
end