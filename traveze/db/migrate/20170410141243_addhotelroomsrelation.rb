class Addhotelroomsrelation < ActiveRecord::Migration[5.0]
  def change
    add_column :rooms, :hotel_id, :integer
  end
end
