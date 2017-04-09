place_list = ["Bangalore","Hyderabad","Chennai","Vijayawada","Indore","Bhopal","Guntur","Coimbatore"]

place_list.each do |place|
    Place.create(name: place)
end
