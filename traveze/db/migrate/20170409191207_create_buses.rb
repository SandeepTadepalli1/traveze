class CreateBuses < ActiveRecord::Migration[5.0]
  def change
    create_table :buses do |t|
      t.time :arrival_time
      t.time :departure_time
      t.integer :bus_type, default: 1200
      t.integer :cost, default: 500
      t.timestamps
    end
  end
end
