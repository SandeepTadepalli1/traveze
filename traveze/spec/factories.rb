FactoryGirl.define do
  factory :manager do
    
  end
  factory :trip do
    
  end
  factory :bus do
    
  end
  factory :room do
    
  end
  factory :hotel do
    
  end
    factory :user do
        name {Faker::Name.first_name}
        email {Faker::Internet.email}
        password "thanks123"
    end

    factory :place do
        name {Faker::Address.city}
    end
end