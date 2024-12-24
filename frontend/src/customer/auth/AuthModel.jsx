import { Box, Modal, Typography } from "@mui/material";
import React from "react";
import RegisterForm from "./RegisterForm";
import LoginForm from "./LoginForm";
import { useLocation } from "react-router-dom";


const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 500,
    bgcolor: 'background.paper',
    outline: "none",
    boxShadow: 24,
    p: 4,
};


const AuthModel = ({handleClose,open}) => {
    const location=useLocation();
  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          {/*<Typography id="modal-modal-title" variant="h6" component="h2">
            Text in a modal
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
          </Typography>*/}

          {location.pathname==="/login"?<LoginForm/>:<RegisterForm/>}


        </Box>
      </Modal>
    </div>
  );
};

export default AuthModel;
