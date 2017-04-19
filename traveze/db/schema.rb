# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20170417091756) do

  create_table "buses", force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8" do |t|
    t.time     "arrival_time"
    t.time     "departure_time"
    t.integer  "bus_type",            default: 1200
    t.integer  "cost",                default: 500
    t.datetime "created_at",                         null: false
    t.datetime "updated_at",                         null: false
    t.integer  "startplace_id"
    t.integer  "destinationplace_id"
    t.index ["destinationplace_id"], name: "index_buses_on_destinationplace_id", using: :btree
    t.index ["startplace_id"], name: "index_buses_on_startplace_id", using: :btree
  end

  create_table "hotel_bookings", force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8" do |t|
    t.integer  "trip_id"
    t.integer  "hotel_id"
    t.integer  "num_days"
    t.integer  "cost"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.date     "start_date"
    t.date     "end_date"
    t.index ["hotel_id"], name: "index_hotel_bookings_on_hotel_id", using: :btree
    t.index ["trip_id"], name: "index_hotel_bookings_on_trip_id", using: :btree
  end

  create_table "hotels", force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8" do |t|
    t.string   "name"
    t.string   "state"
    t.float    "rating",        limit: 24
    t.integer  "numberofrooms"
    t.datetime "created_at",               null: false
    t.datetime "updated_at",               null: false
  end

  create_table "managers", force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8" do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.integer  "user_id"
    t.integer  "hotel_id"
    t.index ["hotel_id"], name: "index_managers_on_hotel_id", using: :btree
    t.index ["user_id"], name: "index_managers_on_user_id", using: :btree
  end

  create_table "places", force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8" do |t|
    t.string   "name"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "rooms", force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8" do |t|
    t.integer  "room_type",  default: 1100
    t.integer  "cost",       default: 1000
    t.datetime "created_at",                null: false
    t.datetime "updated_at",                null: false
    t.integer  "hotel_id"
    t.index ["hotel_id"], name: "index_rooms_on_hotel_id", using: :btree
  end

  create_table "trips", force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8" do |t|
    t.integer  "num_guests"
    t.integer  "trip_cost"
    t.integer  "status",          default: 1500
    t.date     "date_of_booking"
    t.datetime "created_at",                     null: false
    t.datetime "updated_at",                     null: false
    t.integer  "user_id"
    t.index ["user_id"], name: "index_trips_on_user_id", using: :btree
  end

  create_table "users", force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8" do |t|
    t.string   "name"
    t.string   "email"
    t.string   "password_digest"
    t.datetime "created_at",                      null: false
    t.datetime "updated_at",                      null: false
    t.integer  "role",            default: 100
    t.boolean  "is_premium",      default: false
    t.string   "mobilenumber"
    t.index ["email"], name: "index_users_on_email", using: :btree
  end

end
