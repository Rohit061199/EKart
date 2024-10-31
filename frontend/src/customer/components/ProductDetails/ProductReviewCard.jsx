import { Avatar, Box, Grid, Rating } from "@mui/material";
import React from "react";

const ProductReviewCard = () => {
  return (
    <div>
      <Grid container spacing={2} gap={3} justifyContent="flex-start">
        <Grid item xs={1}>
          <Box>
            <Avatar
              className="text-white "
              sx={{ width: 56, height: 56, bgcolor: "#9155fd" }}
            ></Avatar>
          </Box>
        </Grid>
        <Grid item xs={9} alignItems="flex-start">
          <div className="space-y-2 space-x-0">
            <div>
              <p className="font-semibold text-lg text-left">Rohit</p>
              <p className="opacity-70 text-left">October 29, 2024</p>
            </div>
          </div>
          <Box display="flex" justifyContent="flex-start">
            <Rating value={4.5} name="half-rating" precision={0.5} />
          </Box>
          {/*<Rating value={4.5} name="half-rating" precision={0.5}/>*/}
          <p className="font-italic text-left italic">
            Lorem ipsum dolor sit amet consectetur adipisicing elit.
          </p>
        </Grid>
      </Grid>
    </div>
  );
};

export default ProductReviewCard;
