class AddUserToTrips < ActiveRecord::Migration[5.0]
  def change
    add_reference :trips, :user
  end
end
