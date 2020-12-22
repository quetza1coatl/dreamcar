import React from 'react'

import DataTable from 'react-data-table-component'
import DataTableExtensions from 'react-data-table-component-extensions'
import 'react-data-table-component-extensions/dist/index.css'

const Table = ({title, columns, data}) => {
    return <div className='container'>
        <DataTableExtensions data={data} columns={columns} export={false} print={false}>
            <DataTable title={title}
                       columns={columns}
                       data={data}
                       noHeader
                       defaultSortField="id"
                       defaultSortAsc={false}
                       pagination
                       highlightOnHover
            />
        </DataTableExtensions>
    </div>
};

export default Table