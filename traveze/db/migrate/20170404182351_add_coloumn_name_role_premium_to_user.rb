class AddColoumnNameRolePremiumToUser < ActiveRecord::Migration[5.0]
  def change
      add_column :users, :role, :integer,default: 100
      add_column :users, :is_premium, :boolean, default: false
  end
end
