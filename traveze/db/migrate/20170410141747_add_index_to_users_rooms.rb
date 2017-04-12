class AddIndexToUsersRooms < ActiveRecord::Migration[5.0]
  def change
    add_index :rooms, :hotel_id
    add_index :users, :email
  end
end
