class AddStartEndDateToHotelBookings < ActiveRecord::Migration[5.0]
  def change
      add_column :hotel_bookings, :start_date, :date
      add_column :hotel_bookings, :end_date,:date
  end
end
