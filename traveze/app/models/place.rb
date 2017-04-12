class Place < ApplicationRecord
    validates_presence_of :name
    validates :name,uniqueness: true
    before_save :downcase_name


    private
        def downcase_name
            if self.name
                self.name = self.name.delete(' ').downcase
            end
        end

end

