class User < ApplicationRecord
    has_secure_password
    before_save :downcase_email

    validates_presence_of :email,:name
    validates :email, uniqueness: true

    private
    def downcase_email
        if self.email
            self.email = self.email.delete(' ').downcase
        end
    end
end
