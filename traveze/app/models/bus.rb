class Bus < ApplicationRecord
    belongs_to :startplace, :class_name => 'Place', :foreign_key => "startplace_id"
    belongs_to :destinationplace, :class_name => "Place", :foreign_key => "destinationplace_id"
end
