class AddHotelToManagers < ActiveRecord::Migration[5.0]
  def change
    add_reference :managers, :hotel
  end
end
