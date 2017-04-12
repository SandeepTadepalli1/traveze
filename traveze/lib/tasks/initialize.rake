namespace :initialize do
  desc "TODO"
  task test_users: :environment do
    u1 = User.create(:name => "test1", :email => "test1@gmail.com", :password => "thanks123", :password_confirmation =>"thanks123", :mobilenumber =>Faker::Number.number(10))
    u2 = User.create(:name => "test2", :email => "test2@gmail.com", :password => "thanks123", :password_confirmation =>"thanks123", :mobilenumber =>Faker::Number.number(10))
    u3 = User.create(:name => "test3", :email => "test3@gmail.com", :password => "thanks123", :password_confirmation =>"thanks123", :mobilenumber =>Faker::Number.number(10))
  end

  desc "TODO"
  task test_hotels: :environment do
    u1 = User.create(:name => "manager1", :email => "manager1@gmail.com", :password => "thanks123", :password_confirmation =>"thanks123", :mobilenumber =>Faker::Number.number(10))
    u2 = User.create(:name => "manager2", :email => "manager2@gmail.com", :password => "thanks123", :password_confirmation =>"thanks123", :mobilenumber =>Faker::Number.number(10))
    h1 = Hotel.create(:name => "hotel1")
    h2 = Hotel.create(:name => "hotel2")

    h1.create_manager(:user_id=>u2.id)
    h2.create_manager(:user_id=>u1.id)

  end

  task places: :environment do
    place_list = ["Bangalore","Hyderabad","Chennai","Vijayawada","Indore","Bhopal","Guntur","Coimbatore"]

    place_list.each do |place|
      Place.create(name: place)
    end

  end
  task admin: :environment do
      u1 = User.create(:name => "test1", :email => "admin1@gmail.com", :password => "thanks123", :password_confirmation =>"thanks123", :mobilenumber =>Faker::Number.number(10),:role=>ROLE_ADMIN)
  end

    task reset: :environment do
        User.destroy_all
        Manager.destroy_all
        Hotel.destroy_all
    end
end
