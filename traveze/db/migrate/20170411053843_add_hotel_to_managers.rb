class AddHotelToManagers < ActiveRecord::Migration[5.0]
  def change
    add_reference :managers, :hotel, foreign_key: true
  end
end
