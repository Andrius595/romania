import React from "react";
import { useTable, useSortBy } from "react-table";
import Header from "../../components/header";

import {
  ChakraProvider,
  Table,
  Thead,
  Tbody,
  Tr,
  Th,
  Td,
  Flex
} from "@chakra-ui/react";
import { ChevronUpIcon, ChevronDownIcon } from "@chakra-ui/icons";

import makeData from "../data";

function CustomTable({ columns, data }) {
  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    rows,
    prepareRow
  } = useTable(
    {
      columns,
      data
    },
    useSortBy
  );

  const firstPageRows = rows.slice(0, 20);

  return (
    <>
    <Header/>
      <Table {...getTableProps()}>
        <Thead>
          {headerGroups.map((headerGroup) => (
            <Tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map((column) => (
                <Th
                  userSelect="none"
                  {...column.getHeaderProps(column.getSortByToggleProps())}
                >
                  <Flex alignItems="center">
                    {column.render("Header")}
                    {column.isSorted ? (
                      column.isSortedDesc ? (
                        <ChevronDownIcon ml={1} w={4} h={4} />
                      ) : (
                        <ChevronUpIcon ml={1} w={4} h={4} />
                      )
                    ) : (
                      ""
                    )}
                  </Flex>
                </Th>
              ))}
            </Tr>
          ))}
        </Thead>
        <Tbody {...getTableBodyProps()}>
          {firstPageRows.map((row, i) => {
            prepareRow(row);
            return (
              <Tr {...row.getRowProps()}>
                {row.cells.map((cell) => {
                  return (
                    <Td {...cell.getCellProps()}>{cell.render("Cell")}</Td>
                  );
                })}
              </Tr>
            );
          })}
        </Tbody>
      </Table>
      <br />
    </>
  );
}

function App() {
  const columns = React.useMemo(
    () => [
      {
        Header: "Module",
        columns: [
          {
            Header: "Module code",
            accessor: "moduleCode"
          },
          {
            Header: "Module Name",
            accessor: "moduleName"
          }
        ]
      },
      {
        Header: "Info",
        columns: [
          {
            Header: "Reviews",
            accessor: "reviews"
          },
          {
            Header: "Link",
            accessor: "link"
          }
        ]
      }
    ],
    []
  );

  const data = React.useMemo(() => makeData(2000), []);

  return (
    <ChakraProvider>
      <CustomTable columns={columns} data={data} />
    </ChakraProvider>
  );
}

export default App;
