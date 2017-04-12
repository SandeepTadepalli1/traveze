class AddStartAndDestinationToBusses < ActiveRecord::Migration[5.0]
  def change
    add_reference :buses, :startplace
    add_reference :buses, :destinationplace
  end
end
