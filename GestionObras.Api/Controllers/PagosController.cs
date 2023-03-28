using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using GestionObras.Api.DAL;
using GestionObras.Api.Models;

namespace GestionObras.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PagosController : ControllerBase
    {
        private readonly Contexto _context;

        public PagosController(Contexto context)
        {
            _context = context;
        }

        // GET: api/Pagos
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Pagos>>> GetPagos()
        {
            if (_context.Pagos == null)
            {
                return NotFound();
            }
            return await _context.Pagos.ToListAsync();
        }

        // GET: api/Pagos/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Pagos>> GetPagos(int id)
        {
            if (_context.Pagos == null)
            {
                return NotFound();
            }
            var pagos = await _context.Pagos.FindAsync(id);

            if (pagos == null)
            {
                return NotFound();
            }

            return pagos;
        }

        // PUT: api/Pagos/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutPagos(int id, Pagos Pagos)
        {
            if (id != Pagos.PagoId)
            {
                return BadRequest();
            }

            _context.Entry(Pagos).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PagosExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Pagos
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Pagos>> PostPagos(Pagos Pagos)
        {
            if (_context.Pagos == null)
            {
                return Problem("Entity set 'Contexto.Pagos'  is null.");
            }
            _context.Pagos.Add(Pagos);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetPagos", new { id = Pagos.PagoId }, Pagos);
        }

        // DELETE: api/Pagos/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeletePagos(int id)
        {
            if (_context.Pagos == null)
            {
                return NotFound();
            }
            var pagos = await _context.Pagos.FindAsync(id);
            if (pagos == null)
            {
                return NotFound();
            }

            _context.Pagos.Remove(pagos);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool PagosExists(int id)
        {
            return (_context.Pagos?.Any(p => p.PagoId == id)).GetValueOrDefault();
        }
    }
}
