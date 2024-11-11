import { Grid } from '@mui/material'
import React from 'react'
import AdjustIcon from '@mui/icons-material/Adjust';
import { useNavigate } from 'react-router-dom';

const OrderCard = () => {

    const navigate=useNavigate();

  return (
    <div onClick={()=>navigate(`/account/order/${5}`)} className='p-5 shadow-md shadow-black hover:shadow-2xl border'>
      <Grid container spacing sx={{justifyContent:"space-between"}}>
        <Grid item xs={6}>
            <div className='flex cursor-pointer'>
                <img className='w-[5rem] h-[5rem] object-cover object-top' src="https://rukminim1.flixcart.com/image/612/612/xif0q/jean/h/y/g/34-jeans-bt008-laheja-original-imagqqbsfgmdhcvn.jpeg?q=70" alt=""/>

                <div className='ml-5 '>
                    <p className='space-y-2'>Men Slim Mid Rise Black Jeans</p>
                    <p className=' opacity-50 text-xs font-semibold '>Size: L,White</p>
                </div>
            </div>
        </Grid>

        <Grid item xs={2}>
            <p>$199</p>
        </Grid>

        <Grid item xs={4}>
            {true && <div>
                <p>
                <AdjustIcon sx={{width:"15px",height:"15px"}} className='text-green-600 mr-2 text-sm'></AdjustIcon>
                <span> Delivered on November 11</span>
                
            </p>
            <p className='text-xs'>Your item has been Delivered</p>
                </div>}
           {false && <p>
                <span>Expected Delivered on November 11 </span>
            </p>} 
        </Grid>
        
      </Grid>
    </div>
  )
}

export default OrderCard
