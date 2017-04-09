require 'rails_helper'

RSpec.describe User, type: :model do
    it "cannot be created if all fields are blank" do
        u = User.new
        expect(u.save).to eq(false)
    end

    it "cannot be created if email and password are blank" do
        u = User.new(:name => "Sandeep")
        expect(u.save).to eq(false)
    end

    it "validates presence of name" do
        u = User.new(:name => "")
        expect(u.save).to eq (false)
    end

    it "validates uniquess of email" do
        u1 = create(:user)
        u2 = User.new(:name => u1.name,:email => u1.email, :password => "thanks123")
        expect(u2.save).to eq(false)
    end
end
