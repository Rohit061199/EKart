import React, { useEffect } from "react";
import CartItem from "./CartItem";
import { Button, Divider } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { getCart } from "../../../State/Cart/Action";
import { store } from "../../../State/store";

const Cart = () => {

  const navigate=useNavigate();
  const {cart}=useSelector((store)=>store);
  const dispatch=useDispatch();
  const handleCheckout=()=>{
      navigate("/checkout?step=2")
  }

  useEffect(()=>{
    dispatch(getCart());
  },[cart.updateCartItem, cart.deleteCartItem])
  return (
    <div>
      <div className="lg:grid grid-cols-3 lg:px-60 relative">
        <div className="col-span-2">
          {cart.cart?.cartItems.map((item)=><CartItem item={item} />)}
        </div>
        <div className="px-5 sticky top-0 h-[100vh] mt-5 ">
          <div className="border">
            <p className="uppercase font-bold opacity-60 pb-4"> Price Details </p>
            <hr />
            <div className="space-y-3 font-semibold">
                <div className="flex justify-between pt-3 text-black">
                    <span>Price</span>
                    <span>₹{cart.cart?.totalPrice}</span>
                </div>
                <div className="flex justify-between pt-3 ">
                    <span>Discount</span>
                    <span className="text-green-600">-₹{cart.cart?.discount}</span>
                </div>
                <div className="flex justify-between pt-3 text-black">
                    <span>Delivery Price</span>
                    <span className="text-green-600">Free</span>
                </div>
                <div className="flex justify-between pt-3 text-black">
                    <span>Total Amount</span>
                    <span className="text-green-600">₹{cart.cart?.totalDiscountedPrice}</span>
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

                  onClick={handleCheckout}
                >
                  Proceed To Payment
                </Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Cart;
