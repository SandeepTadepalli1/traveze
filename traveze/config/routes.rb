Rails.application.routes.draw do
  post 'admin/add_hotel'
  post 'user/create_trip'
  post 'user/update'
  post 'user/register'

  post 'edit_hotel', to: "manager#edit_hotel"
  post 'update_room_cost', to: "manager#update_room_cost"
  post 'rooms_booked', to: "manager#num_rooms_booked_given_date"

  post 'authenticate', to: 'authentication#authenticate'
  get 'username', to: 'test#username'
  post 'place/hotels'

end
