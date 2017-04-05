class TestController < ApplicationController
    def username
        render json: {user: @current_user, status: STATUS_OK}
    end
end