class CreateHotels < ActiveRecord::Migration[5.0]
  def change
    create_table :hotels do |t|
      t.string :name
      t.string :state
      t.float :rating
      t.integer :numberofrooms
      t.timestamps
    end
  end
end
