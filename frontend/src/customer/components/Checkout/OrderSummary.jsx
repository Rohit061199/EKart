import React from "react";
import AddressCard from "../AddressCard/AddressCard";
import CartItem from "../Cart/CartItem";
import { Button } from "@mui/material";

const OrderSummary = () => {
  return (
    <div>
      <div className="p-5 shadow-lg rounded-md border">
        <AddressCard />
      </div>
      <div>
        <div className="lg:grid grid-cols-3 relative">
          <div className="col-span-2">
            {[1, 1, 1, 1].map((item) => (
              <CartItem />
            ))}
          </div>
          <div className="px-5 sticky top-0 h-[100vh] mt-5 ">
            <div className="border">
              <p className="uppercase font-bold opacity-60 pb-4">
                {" "}
                Price Details{" "}
              </p>
              <hr />
              <div className="space-y-3 font-semibold">
                <div className="flex justify-between pt-3 text-black">
                  <span>Price</span>
                  <span>₹5000</span>
                </div>
                <div className="flex justify-between pt-3 ">
                  <span>Discount</span>
                  <span className="text-green-600">-₹2000</span>
                </div>
                <div className="flex justify-between pt-3 text-black">
                  <span>Delivery Price</span>
                  <span className="text-green-600">Free</span>
                </div>
                <div className="flex justify-between pt-3 text-black">
                  <span>Total Amount</span>
                  <span className="text-green-600">₹3000</span>
                </div>
              </div>

              <Button
                variant="contained"
                sx={{
                  px: "2rem",
                  py: "1rem",
                  bgcolor: "#9155fd",
                  mt: "1rem",
                }}
                className="w-full mt-5 mb-10"
              >
                Proceed To Payment
              </Button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OrderSummary;
