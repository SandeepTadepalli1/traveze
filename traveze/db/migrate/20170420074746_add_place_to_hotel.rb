class AddPlaceToHotel < ActiveRecord::Migration[5.0]
  def change
    add_reference :hotels, :place
  end
end
