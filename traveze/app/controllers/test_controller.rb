class TestController < ApplicationController
    def username
        m = Manager.first
        render json: {manager: m.as_json(include: [:hotel,:user]), status: STATUS_OK}
    end
end