import { Fragment, useEffect, useState } from "react";
import { Dialog, Popover, Tab, Transition } from "@headlessui/react";
import {
  Bars3Icon,
  MagnifyingGlassIcon,
  ShoppingBagIcon,
  XMarkIcon,
} from "@heroicons/react/24/outline";

import { navigationData as navigation } from "./navigation";


import { Avatar, Button, Menu, MenuItem } from "@mui/material";

import { deepPurple } from "@mui/material/colors";

import TextField from "@mui/material/TextField";

function classNames(...classes) {
  return classes.filter(Boolean).join(" ");
}

export default function Navigation() {
  const [open, setOpen] = useState(false);
  const [openAuthModal, setOpenAuthModal] = useState(false);
  const [anchorEl, setAnchorEl] = useState(null);
  const openUserMenu = Boolean(anchorEl);
  const jwt = localStorage.getItem("jwt");

  const handleUserClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleCloseUserMenu = (event) => {
    setAnchorEl(null);
  };

  const handleOpen = () => {
    setOpenAuthModal(true);
  };
  const handleClose = () => {
    setOpenAuthModal(false);
  };

  const handleCategoryClick = (category, section, item, close) => {
    //navigate(`/${category.id}/${section.id}/${item.id}`);
    close();
  };

  return (
    <div className="bg-white pb-10">
      {/* Mobile menu */}
      <Transition.Root show={open} as={Fragment}>
        <Dialog as="div" className="relative z-40 lg:hidden" onClose={setOpen}>
          <Transition.Child
            as={Fragment}
            enter="transition-opacity ease-linear duration-300"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="transition-opacity ease-linear duration-300"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <div className="fixed inset-0 bg-black bg-opacity-25" />
          </Transition.Child>

          <div className="fixed inset-0 z-40 flex">
            <Transition.Child
              as={Fragment}
              enter="transition ease-in-out duration-300 transform"
              enterFrom="-translate-x-full"
              enterTo="translate-x-0"
              leave="transition ease-in-out duration-300 transform"
              leaveFrom="translate-x-0"
              leaveTo="-translate-x-full"
            >
              <Dialog.Panel className="relative flex w-full max-w-xs flex-col overflow-y-auto bg-white pb-12 shadow-xl">
                <div className="flex px-4 pb-2 pt-5">
                  <button
                    type="button"
                    className="-m-2 inline-flex items-center justify-center rounded-md p-2 text-gray-400"
                    onClick={() => setOpen(false)}
                  >
                    <span className="sr-only">Close menu</span>
                    <XMarkIcon className="h-6 w-6" aria-hidden="true" />
                  </button>
                </div>

                {/* Links */}
                <Tab.Group as="div" className="mt-2">
                  <div className="border-b border-gray-200">
                    <Tab.List className="-mb-px flex space-x-8 px-4">
                      {navigation.categories.map((category) => (
                        <Tab
                          key={category.name}
                          className={({ selected }) =>
                            classNames(
                              selected
                                ? "border-indigo-600 text-indigo-600"
                                : "border-transparent text-gray-900",
                              "flex-1 whitespace-nowrap border-b-2 px-1 py-4 text-base font-medium border-none"
                            )
                          }
                        >
                          {category.name}
                        </Tab>
                      ))}
                    </Tab.List>
                  </div>
                  <Tab.Panels as={Fragment}>
                    {navigation.categories.map((category) => (
                      <Tab.Panel
                        key={category.name}
                        className="space-y-10 px-4 pb-8 pt-10"
                      >
                        <div className="grid grid-cols-2 gap-x-4">
                          {category.featured.map((item) => (
                            <div
                              key={item.name}
                              className="group relative text-sm"
                            >
                              <div className="aspect-h-1 aspect-w-1 overflow-hidden rounded-lg bg-gray-100 group-hover:opacity-75">
                                <img
                                  src={item.imageSrc}
                                  alt={item.imageAlt}
                                  className="object-cover object-center"
                                />
                              </div>
                              <a
                                href={item.href}
                                className="mt-6 block font-medium text-gray-900"
                              >
                                <span
                                  className="absolute inset-0 z-10"
                                  aria-hidden="true"
                                />
                                {item.name}
                              </a>
                              <p aria-hidden="true" className="mt-1">
                                Shop now
                              </p>
                            </div>
                          ))}
                        </div>
                        {category.sections.map((section) => (
                          <div key={section.name}>
                            <p
                              id={`${category.id}-${section.id}-heading-mobile`}
                              className="font-medium text-gray-900"
                            >
                              {section.name}
                            </p>
                            {/* eslint-disable-next-line jsx-a11y/no-redundant-roles */}
                            <ul
                              role="list"
                              aria-labelledby={`${category.id}-${section.id}-heading-mobile`}
                              className="mt-6 flex flex-col space-y-6"
                            >
                              {section.items.map((item) => (
                                <li key={item.name} className="flow-root">
                                  <p className="-m-2 block p-2 text-gray-500">
                                    {"item.name"}
                                  </p>
                                </li>
                              ))}
                            </ul>
                          </div>
                        ))}
                      </Tab.Panel>
                    ))}
                  </Tab.Panels>
                </Tab.Group>

                <div className="space-y-6 border-t border-gray-200 px-4 py-6">
                  {navigation.pages.map((page) => (
                    <div key={page.name} className="flow-root">
                      <a
                        href={page.href}
                        className="-m-2 block p-2 font-medium text-gray-900"
                      >
                        {page.name}
                      </a>
                    </div>
                  ))}
                </div>

                <div className="space-y-6 border-t border-gray-200 px-4 py-6">
                  <div className="flow-root">
                    <a
                      href="/"
                      className="-m-2 block p-2 font-medium text-gray-900"
                    >
                      Sign in
                    </a>
                  </div>
                </div>

                <div className="border-t border-gray-200 px-4 py-6">
                  <a href="/" className="-m-2 flex items-center p-2">
                    <img
                      src="https://tailwindui.com/img/flags/flag-canada.svg"
                      alt=""
                      className="block h-auto w-5 flex-shrink-0"
                    />
                    <span className="ml-3 block text-base font-medium text-gray-900">
                      CAD
                    </span>
                    <span className="sr-only">, change currency</span>
                  </a>
                </div>
              </Dialog.Panel>
            </Transition.Child>
          </div>
        </Dialog>
      </Transition.Root>

      <header className="relative bg-white">
        <p className="flex h-10 items-center justify-center bg-indigo-600 px-4 text-sm font-medium text-white sm:px-6 lg:px-8">
          Get free delivery on orders over $100
        </p>

        <nav aria-label="Top" className="mx-auto">
          <div className="border-b border-gray-200">
            <div className="flex h-16 items-center px-11">
              <button
                type="button"
                className="rounded-md bg-white p-2 text-gray-400 lg:hidden"
                onClick={() => setOpen(true)}
              >
                <span className="sr-only">Open menu</span>
                <Bars3Icon className="h-6 w-6" aria-hidden="true" />
              </button>

              {/* Logo */}
              <div className="ml-4 flex lg:ml-0">
                
                  <span className="sr-only">Your Company</span>
                  <img
                    src="data:image/png;base64,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"
                    alt="E-Kart Online Shopping"
                    className="h-8 w-8 mr-2"
                  />
                
              </div>

              {/* Flyout menus */}
              <Popover.Group className="hidden lg:ml-8 lg:block lg:self-stretch z-10">
                <div className="flex h-full space-x-8">
                  {navigation.categories.map((category) => (
                    <Popover key={category.name} className="flex">
                      {({ open, close }) => (
                        <>
                          <div className="relative flex">
                            <Popover.Button
                              className={classNames(
                                open
                                  ? "border-indigo-600 text-indigo-600"
                                  : "border-transparent text-gray-700 hover:text-gray-800",
                                "relative z-10 -mb-px flex items-center border-b-2 pt-px text-sm font-medium transition-colors duration-200 ease-out"
                              )}
                            >
                              {category.name}
                            </Popover.Button>
                          </div>

                          <Transition
                            as={Fragment}
                            enter="transition ease-out duration-200"
                            enterFrom="opacity-0"
                            enterTo="opacity-100"
                            leave="transition ease-in duration-150"
                            leaveFrom="opacity-100"
                            leaveTo="opacity-0"
                          >
                            <Popover.Panel className="absolute inset-x-0 top-full text-sm text-gray-500">
                              {/* Presentational element used to render the bottom shadow, if we put the shadow on the actual panel it pokes out the top, so we use this shorter element to hide the top of the shadow */}
                              <div
                                className="absolute inset-0 top-1/2 bg-white shadow"
                                aria-hidden="true"
                              />

                              <div className="relative bg-white">
                                <div className="mx-auto max-w-7xl px-8">
                                  <div className="grid grid-cols-2 gap-x-8 gap-y-10 py-16">
                                    <div className="col-start-2 grid grid-cols-2 gap-x-8">
                                      {category.featured.map((item) => (
                                        <div
                                          key={item.name}
                                          className="group relative text-base sm:text-sm"
                                        >
                                          <div className="aspect-h-1 aspect-w-1 overflow-hidden rounded-lg bg-gray-100 group-hover:opacity-75">
                                            <img
                                              src={item.imageSrc}
                                              alt={item.imageAlt}
                                              className="object-cover object-center"
                                            />
                                          </div>
                                          <a
                                            href={item.href}
                                            className="mt-6 block font-medium text-gray-900"
                                          >
                                            <span
                                              className="absolute inset-0 z-10"
                                              aria-hidden="true"
                                            />
                                            {item.name}
                                          </a>
                                          <p
                                            aria-hidden="true"
                                            className="mt-1"
                                          >
                                            Shop now
                                          </p>
                                        </div>
                                      ))}
                                    </div>
                                    <div className="row-start-1 grid grid-cols-3 gap-x-8 gap-y-10 text-sm">
                                      {category.sections.map((section) => (
                                        <div key={section.name}>
                                          <p
                                            id={`${section.name}-heading`}
                                            className="font-medium text-gray-900"
                                          >
                                            {section.name}
                                          </p>
                                          {/* eslint-disable-next-line jsx-a11y/no-redundant-roles */}
                                          <ul
                                            role="list"
                                            aria-labelledby={`${section.name}-heading`}
                                            className="mt-6 space-y-6 sm:mt-4 sm:space-y-4"
                                          >
                                            {section.items.map((item) => (
                                              <li
                                                key={item.name}
                                                className="flex"
                                              >
                                                <p
                                                  onClick={() =>
                                                    handleCategoryClick(
                                                      category,
                                                      section,
                                                      item,
                                                      close
                                                    )
                                                  }
                                                  className="cursor-pointer hover:text-gray-800"
                                                >
                                                  {item.name}
                                                </p>
                                              </li>
                                            ))}
                                          </ul>
                                        </div>
                                      ))}
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </Popover.Panel>
                          </Transition>
                        </>
                      )}
                    </Popover>
                  ))}

                  {navigation.pages.map((page) => (
                    <a
                      key={page.name}
                      href={page.href}
                      className="flex items-center text-sm font-medium text-gray-700 hover:text-gray-800"
                    >
                      {page.name}
                    </a>
                  ))}
                </div>
              </Popover.Group>

              <div className="ml-auto flex items-center">
                <div className="hidden lg:flex lg:flex-1 lg:items-center lg:justify-end lg:space-x-6">
                  {true ? (
                    <div>
                      <Avatar
                        className="text-white"
                        onClick={handleUserClick}
                        aria-controls={open ? "basic-menu" : undefined}
                        aria-haspopup="true"
                        aria-expanded={open ? "true" : undefined}
                        // onClick={handleUserClick}
                        sx={{
                          bgcolor: deepPurple[500],
                          color: "white",
                          cursor: "pointer",
                        }}
                      >
                        R
                      </Avatar>
                      {/* <Button
                        id="basic-button"
                        aria-controls={open ? "basic-menu" : undefined}
                        aria-haspopup="true"
                        aria-expanded={open ? "true" : undefined}
                        onClick={handleUserClick}
                      >
                        Dashboard
                      </Button> */}
                      <Menu
                        id="basic-menu"
                        anchorEl={anchorEl}
                        open={openUserMenu}
                        onClose={handleCloseUserMenu}
                        MenuListProps={{
                          "aria-labelledby": "basic-button",
                        }}
                      >
                        <MenuItem >
                          My Orders
                        </MenuItem>
                        <MenuItem >Logout</MenuItem>
                      </Menu>
                    </div>
                  ) : (
                    <Button
                      onClick={handleOpen}
                      className="text-sm font-medium text-gray-700 hover:text-gray-800"
                    >
                      Signin
                    </Button>
                  )}
                </div>

                {/* Search */}
                <div className="flex items-center lg:ml-6">
                
                  <p  className="p-2 text-gray-400 hover:text-gray-500">
                    <span className="sr-only">Search</span>
                    
                    <MagnifyingGlassIcon
                      className="h-6 w-6"
                      aria-hidden="true"
                    />
                  </p>
                </div>

                {/* Cart */}
                <div className="ml-4 flow-root lg:ml-6">
                  <Button
                    
                    className="group -m-2 flex items-center p-2"
                  >
                    <ShoppingBagIcon
                      className="h-6 w-6 flex-shrink-0 text-gray-400 group-hover:text-gray-500"
                      aria-hidden="true"
                    />
                    <span className="ml-2 text-sm font-medium text-gray-700 group-hover:text-gray-800">
                      2
                    </span>
                    <span className="sr-only">items in cart, view bag</span>
                  </Button>
                </div>
              </div>
            </div>
          </div>
        </nav>
      </header>
      
    </div>
  );
}