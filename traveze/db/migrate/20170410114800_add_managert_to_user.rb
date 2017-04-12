class AddManagertToUser < ActiveRecord::Migration[5.0]
  def change
    add_reference :managers, :user,index: true
  end
end
