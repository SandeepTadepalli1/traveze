class CreateTrips < ActiveRecord::Migration[5.0]
  def change
    create_table :trips do |t|
      t.integer :num_guests
      t.integer :trip_cost
      t.integer :status , default: 1500
      t.date    :date_of_booking
      t.timestamps
    end
  end
end
