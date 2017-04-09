FactoryGirl.define do
    factory :user do
        name {Faker::Name.first_name}
        email {Faker::Internet.email}
        password "thanks123"
    end

    factory :place do
        name {Faker::Address.city}
    end
end