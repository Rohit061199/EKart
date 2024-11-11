import React from 'react'
import AddressCard from '../AddressCard/AddressCard'
import OrderTracker from './OrderTracker'
import { Box, Grid } from '@mui/material'
import { deepPurple } from '@mui/material/colors'
import StarBorderIcon from '@mui/icons-material/StarBorder';

const OrderDetails = () => {
  return (
    <div className='px-5 lg:px-20'>
        <div>
            <h1 className='font-bold text-lg py-7'> Delivery Address</h1>
            <AddressCard/>
        </div>
        <div className='py-20'>
            <OrderTracker activeStep={3}/>
        </div>

       {[1,1,1,1,1].map((item)=> <Grid container className='space-x-5 py-2'>

            <Grid item container className='shadow-xl rounded-md p-5 border ' sx={{alignItems:"center", justifyContent:"space-between"}}>
                <Grid item xs={6}>
                    <div className='flex items-center space-x-4'>
                        <img className='w-[5rem] h-[5rem] object-cover object-top'src='https://rukminim1.flixcart.com/image/612/612/xif0q/jean/h/y/g/34-jeans-bt008-laheja-original-imagqqbsfgmdhcvn.jpeg?q=70' alt=''/>
                        <div className='text-left py-2'>
                            <p className='font-semibold'>Men Slim Mid Rise Black Jeans</p>
                            <p className='space-x-5 opacity-50 text xs font-semibold'><span>Size: L </span><span>Color: White</span></p>
                            <p>$199</p>
                        </div>
                    </div>
                </Grid>

                <Grid item>
                    <Box sx={{color:deepPurple[500]}}>
                        <StarBorderIcon sx={{fontSize:"2rem"}} className='px-2'/>
                        
                        <span> Rate & Review Product</span>
                    </Box>
                </Grid>
            </Grid>
        </Grid>)}
      
    </div>
  )
}

export default OrderDetails
