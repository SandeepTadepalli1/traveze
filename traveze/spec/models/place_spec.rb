require 'rails_helper'

RSpec.describe Place, type: :model do
  it "Validates presence of name" do
      place = Place.new
      expect(place).not_to be_valid
  end

    it "Validates uniqueness of place" do
        p1 = create(:place)
        p2 = Place.new(:name => p1.name)
        expect(p2.save).to match false
    end
end
