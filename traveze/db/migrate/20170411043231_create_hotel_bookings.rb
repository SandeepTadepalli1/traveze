class CreateHotelBookings < ActiveRecord::Migration[5.0]
  def change
    create_table :hotel_bookings do |t|
      t.belongs_to :trip
      t.belongs_to :hotel
      t.integer :num_days
      t.integer :cost
      t.timestamps
    end
  end
end
