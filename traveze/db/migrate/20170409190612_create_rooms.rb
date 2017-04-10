class CreateRooms < ActiveRecord::Migration[5.0]
  def change
    create_table :rooms do |t|
      t.integer :room_type, default: 1100
      t.integer :cost, default: 1000
      t.timestamps
    end
  end
end
