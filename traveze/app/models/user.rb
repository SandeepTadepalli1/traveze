class User < ApplicationRecord
    has_secure_password
    before_save :downcase_email

    validates_presence_of :email,:name,:mobilenumber
    validates :email, uniqueness: true
    has_many :trips


    def is_manager
        if self.role != ROLE_HOTEL_MANAGER
            false
        else
           true
        end
    end


    def to_json(options = {})
        super(options.merge({ except: [:password_digest]}))
    end

    private
    def downcase_email
        if self.email
            self.email = self.email.delete(' ').downcase
        end
    end

end
